import scala.util.{Failure, Success, Try}

/**
  * Created by immo on 07/12/15.
  */


object Main extends App {

  case class Position(x: Int, y: Int)


  def nextPosition(inp: String, actualPosition: Position): Position =
    inp head match {
      case '^' => Position(actualPosition.x, actualPosition.y + 1)
      case 'v' => Position(actualPosition.x, actualPosition.y - 1)
      case '>' => Position(actualPosition.x + 1, actualPosition.y)
      case '<' => Position(actualPosition.x - 1, actualPosition.y)
    }

  def deliver(inp: String): List[Position] = {
    def loop(inp: String, housesDelivered: List[Position], actualPosition: Position): List[Position] = {
      val actualList = housesDelivered contains actualPosition match {
        case true => housesDelivered
        case false => actualPosition :: housesDelivered
      }

      Try {nextPosition(inp, actualPosition)} match {
        case Success(nextHouse) => loop(inp tail, actualList, nextHouse)
        case Failure(_) => actualList
      }
    }

    loop(inp, Nil, Position(0,0))

  }

  val houses = scala.io.Source.fromFile("input.txt").mkString
  println(deliver(houses).size)


}
