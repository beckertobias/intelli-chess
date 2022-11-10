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

    @GetMapping("/chessMagic")
    fun chessMagic() {
        // Creates a new chessboard in the standard initial position
        // Creates a new chessboard in the standard initial position
        val board = Board()

        // Make a move from E2 to E4 squares
        board.doMove(Move(Square.E2, Square.E4))

        board.doMove(Move(Square.E7, Square.E5))
        // print the chessboard in a human-readable form

        // print the chessboard in a human-readable form
        System.out.println(board.toString())
    }
}
