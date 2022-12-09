package chessdt.chesswithdyegoandtobi

import chessdt.chesswithdyegoandtobi.domain.MoveArguments
import com.github.bhlangonijr.chesslib.Board
import com.github.bhlangonijr.chesslib.Square
import com.github.bhlangonijr.chesslib.move.Move
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    // create new board
    val board = Board()

    @GetMapping("/hello")
    fun sayHelloTo(name: String): String {
        return "HELLO DYEGO! $name"
    }

    @GetMapping("/moves/getLegal")
    fun getLegalMoves(currentFigure: String): List<Move> {
        val allLegalMoves = board.legalMoves()
        return allLegalMoves.filter { it.toString().startsWith(currentFigure.lowercase()) }
    }

    @PostMapping("/chessMagic")
    fun chessMagic(
        @RequestBody moveArguments: MoveArguments
    ): String {
        val startPosition = moveArguments.startPosition
        val endPosition = moveArguments.endPosition

        val startSquare = Square.valueOf(startPosition)
        val endSquare = Square.valueOf(endPosition)

        // check if move is legal
        val legalMoves = getLegalMoves(startPosition)
        val selectedMove = Move(startSquare, endSquare)
        val isSelectedMoveLegal = selectedMove in legalMoves

        // Make a move if legal
        if (isSelectedMoveLegal) {
            board.doMove(Move(startSquare, endSquare))
        } else {
            print("Fuck Off")
        }

        // print the chessboard in a human-readable form
        return board.toString()
    }
}
