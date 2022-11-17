package chessdt.chesswithdyegoandtobi

import com.github.bhlangonijr.chesslib.Board
import com.github.bhlangonijr.chesslib.Square
import com.github.bhlangonijr.chesslib.move.Move
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun sayHelloTo(name: String): String {
        return "HELLO DYEGO! $name"
    }

    @GetMapping("/moves/getLegal")
    fun getLegalMoves(currentFigure: String): List<Move> {
        val board = Board()
        val allLegalMoves = board.legalMoves()
        val legalMovesForCurrentFigure = allLegalMoves.filter { it.toString().startsWith(currentFigure) }

        return legalMovesForCurrentFigure
    }

    @GetMapping("/chessMagic")
    fun chessMagic(): String {
        // Creates a new chessboard in the standard initial position
        // Creates a new chessboard in the standard initial position
        val board = Board()

        // Make a move from E2 to E4 squares
        board.doMove(Move(Square.E2, Square.E4))

        board.doMove(Move(Square.B7, Square.B4))

        // print the chessboard in a human-readable form

        val newBoard = board.toString()
        // print the chessboard in a human-readable form

        return newBoard
    }
}
