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

  def doDeliver(actualPosition: Position, deliveries: List[Position]) =
    deliveries contains actualPosition match {
      case true => deliveries
      case false => actualPosition :: deliveries
    }

  def deliver(inp: String): List[Position] = {
    def loop(inp: String, housesDelivered: List[Position], realSanta: Position): List[Position] = {
      val actualList = doDeliver(realSanta, housesDelivered)

      val newRealSantaPosition = Try {
        nextPosition(inp, realSanta)
      }

      newRealSantaPosition match {
        case Success(nextHouse) => loop(inp.tail.tail, actualList, nextHouse)
        case Failure(_) => actualList
      }
    }

    loop(inp, Nil, Position(0, 0))
  }

  def deliverRobot(inp: String): List[Position] = {
    def loop(inp: String, housesDelivered: List[Position], robotSanta: Position, realSanta: Position): List[Position] = {
      val actualList = doDeliver(robotSanta, doDeliver(realSanta, housesDelivered))

      val newRealSantaPosition = Try {
        nextPosition(inp, realSanta)
      }
      val newRobotSantaPosition = Try {
        nextPosition(inp.tail, robotSanta)
      }


      (newRealSantaPosition, newRobotSantaPosition) match {
        case (Success(realNextHouse), Success(robotNextHouse)) => loop(inp.tail.tail, actualList, robotNextHouse, realNextHouse)
        case (Success(realNextHouse), Failure(_)) => loop(inp.tail.tail, actualList, robotSanta, realNextHouse)
        case (Failure(_), Failure(_)) => actualList
      }
    }

    loop(inp, Nil, Position(0, 0), Position(0, 0))
  }

  val houses = scala.io.Source.fromFile("input.txt").mkString
  println(deliver(houses).size)


}
