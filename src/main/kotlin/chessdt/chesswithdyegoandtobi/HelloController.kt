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
        val startSquare = Square.valueOf(moveArguments.startPosition)
        val endSquare = Square.valueOf(moveArguments.endPosition)

        // check if move is legal
        val legalMoves = getLegalMoves(moveArguments.startPosition)
        val selectedMove = Move(startSquare, endSquare)

        // Make a move if legal
        if (selectedMove.isLegal(legalMoves)) {
            board.doMove(Move(startSquare, endSquare))
        } else {
            print("Fuck Off")
        }

        return board.fen
    }

    fun Move.isLegal(legalMoves: List<Move>) = this in legalMoves
}
