package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs()).isZero().withFailMessage("Debe ser 0");
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        assertThatThrownBy(() -> tested.putDiscInColumn(7))
                .isInstanceOf(RuntimeException.class);/*.hasMessageContaining("No more room");*/
        assertThatThrownBy(() -> tested.putDiscInColumn(-1))
                .isInstanceOf(RuntimeException.class);

        //desbordamiento de columna
        assertThatThrownBy(() -> {
            for (int i = 0; i < 7; i++) {
                tested.putDiscInColumn(3);
            }
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("No more room");


    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        for (int i = 0; i < 7; i++) {
            assertThat(tested.putDiscInColumn(i)).isEqualTo(0);
        }
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        for (int i = 0; i < 7; i++) {
            tested.putDiscInColumn(i);
            assertThat(tested.putDiscInColumn(i)).isEqualTo(1);
        }
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() { //este bien pero hay que pensar
        int discosIniciales=0;
        for (int i = 0; i < 7; i++) {
            tested.putDiscInColumn(i);
            assertThat(tested.getNumberOfDiscs()).isEqualTo(discosIniciales+1+i);
        }

    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        assertThatThrownBy(() -> {
            for (int i = 0; i < 7; i++) {
                tested.putDiscInColumn(3);
            }
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("No more room in column 3");


    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {

        assertThat(tested.getCurrentPlayer()).isEqualTo("R");
        assertThat(tested.putDiscInColumn(2)).isEqualTo(0);


    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {

        assertThat(tested.getCurrentPlayer()).isEqualTo("R");
        assertThat(tested.putDiscInColumn(2)).isEqualTo(0);
        assertThat(tested.getCurrentPlayer()).isEqualTo("G");
        assertThat(tested.putDiscInColumn(2)).isEqualTo(1);

    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {

        String currentPlayer = tested.getCurrentPlayer();
        String mensajeOutput=String.format("Player %s turn%n", currentPlayer);
        assertThat(output.toString()).isEqualTo(mensajeOutput);
            }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {

        tested.putDiscInColumn(0);
        //System.out.println(output.toString());
        String expected = """
                | | | | | | | |
                | | | | | | | |
                | | | | | | | |
                | | | | | | | |
                | | | | | | | |
                |R| | | | | | |
                """;


        assertThat(output.toString()).isEqualTo(expected);

        tested.putDiscInColumn(0);
        String expected2 = """
                | | | | | | | |
                | | | | | | | |
                | | | | | | | |
                | | | | | | | |
                |G| | | | | | |
                |R| | | | | | |
                """;

        assertThat(output.toString().replace("\r", "").replace(expected,"")).isEqualTo(expected2);

    }
    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {

            assertThat(tested.isFinished()).isEqualTo(false);

    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
           //*** llenar tablero sin que gane nadie y comprobar

        final PrintStream limpiar = System.out;
        System.setOut(limpiar);
        for (int i = 0; i < 6; i++) {
            int n =3;
            for (int j=0; j<3;j++){
                tested.putDiscInColumn(j);
                tested.putDiscInColumn(j+n);
                }
            tested.putDiscInColumn(6);
          n -= 1;
        }
        String expected = """
                 |G|G|G|R|R|R|G|
                 |R|R|R|G|G|G|R|
                 |G|G|G|R|R|R|G|
                 |R|R|R|G|G|G|R|
                 |G|G|G|R|R|R|G|
                 |R|R|R|G|G|G|R|
                 """;

        assertThat(output.toString().equals(expected));

        System.setOut(limpiar);

        assertThat(tested.isFinished()).isEqualTo(true);

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        int n =1;
        tested.getWinner();
        for (int i = 0; i < 4 && tested.getWinner()==""; i++) {
            tested.putDiscInColumn(0);
            tested.putDiscInColumn(i+n);
            tested.getWinner();
        }
       assertThat(tested.getWinner()).isEqualTo("R");
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        int i = 0;
        int n =4;
        tested.getWinner();
        for (int j=0; j<4  && tested.getWinner()=="" ;j++){
               tested.putDiscInColumn(j);
               if(j!=3) {
                    tested.putDiscInColumn(j + n);
                   tested.getWinner();
                }
            }

        assertThat(tested.getWinner()).isEqualTo("R");


    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        final PrintStream limpiar = System.out;
        System.setOut(limpiar);
        int i=0;
        tested.getWinner();
        for(i=0;i<4 && tested.getWinner()=="" ;i++){
           int j=6;
           for (j=6;j>1 && tested.getWinner()=="";j--) {
                tested.putDiscInColumn(j);
                tested.getWinner();


            }

        }
        assertThat(tested.getWinner()).isEqualTo("G");
        String expected = """
                 | | | | | | | |
                 | | | | | | | |
                 | | | | | | |G|
                 | | |R|G|R|G|R|
                 | | |G|R|G|R|G|
                 | | |R|G|R|G|R|
                 """;

        assertThat(output.toString().equals(expected));
        System.setOut(limpiar);
    }




    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        int i,j;
        for(i=0;i<4;i++){
            for (j=0;j<5;j++){
                tested.putDiscInColumn(j);
                if (i==3){
                    j=4;
                }
            }
        }
        assertThat(tested.getWinner()).isEqualTo("G");

    }
}
