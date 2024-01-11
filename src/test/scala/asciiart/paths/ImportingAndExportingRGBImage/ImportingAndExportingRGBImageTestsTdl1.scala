package asciiart.paths.ImportingAndExportingRGBImage

import asciiart.{PathTestsGroup, StandardTestsGroup}
import asciiart.controllers.ConsoleController
import asciiart.ui.ConsoleView
import org.scalatest.FunSuite

import java.nio.file.{Files, Paths}
//case class TestableConsoleViewTests()




class ImportingAndExportingRGBImageTestsTdl1 extends FunSuite {
  // TDL=1

  val baseSamplePath = "samples/"
  val baseOutputPath = baseSamplePath + "artifacts/"

  test("TDL=1; path: 4", PathTestsGroup) {

    val controller = new ConsoleController()
    val args = List("--help")
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    val output = "Usage: run [options]\n\nOptions:\n--image <image_path> Path to the image file to be processed. Supported image formats are JPG and PNG.\n--image-random Generate a random image.\n--output-file <output_path> Path to the output file. The output file must be a TXT file.\n--output-console Print ascii image to standard output.\n--rotate <degrees> Rotate the image by the specified angle. The angle must be a valid number divisible by 90.\n--scale <factor> Scale the image by the specified factor. Supported factors are 0.25, 1, 4.\n--flip <direction> Flip the image horizontally (x) or vertically (y).\n--invert Invert the image.\n--table <table_type> Convert the image to ASCII art using the specified table. Supported table types are 'paulbourke' and 'nonlinear'. Default is 'paulbourke'.\n--custom-table <table_path> Convert the image to ASCII art using the specified custom table.\n\nExample:\nprogram --image test.jpg --rotate 90 --output-file test.txt\nprogram --image-random --scale 0.25 --flip y --table paulbourke\nprogram --custom-table 1{}[]?-_+~<>i!lI;:, --output-console test.txt"

    assert(controller.getLastMessage().contains(output))
  }


  test("TDL=1; path: 1 - 2 - 5 - 11", PathTestsGroup) {
    val controller = new ConsoleController()
    val args = List("--image", "path/to/nonexistent.png")
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    val expectedErrorMessage = "Unable to load image with provided path: 'path/to/nonexistent.png'"
    assert(controller.getLastErrorMessage.contains(expectedErrorMessage))
  }


  test("TDL=1; path: 1 - 2 - 6 - 12", PathTestsGroup) {
    val controller = new ConsoleController()
    val args = List("--image", "path/to/nonexistent.jpg")
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    val expectedErrorMessage = "Unable to load image with provided path: 'path/to/nonexistent.jpg'"
    assert(controller.getLastErrorMessage.contains(expectedErrorMessage))
  }


  test("TDL=1; path: 1 - 2 - 7 - 13", PathTestsGroup) {
    val controller = new ConsoleController()
    val args = List("--image", "path/to/nonexistent.gif")
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    val expectedErrorMessage = "Unable to load image with provided path: 'path/to/nonexistent.gif'"
    assert(controller.getLastErrorMessage.contains(expectedErrorMessage))
  }


  test("TDL=1; path: 1 - 2 - 8 - 14", PathTestsGroup) {
    val controller = new ConsoleController()
    val args = List("--image", "path/to/nonexistent.bmp")
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    val expectedErrorMessage = "Unable to load image with provided path: 'path/to/nonexistent.bmp'"
    assert(controller.getLastErrorMessage.contains(expectedErrorMessage))
  }


  test("TDL=1; path: 1 - 3 - 9 - 15 - 17", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.bmp")
    val args = List("--image-random", "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 3 - 10 - 16 - 17", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.bmp")
    val args = List("--image-random-gradient", "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 5 - 21 - 17", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.bmp")
    val inputPath = Paths.get(baseSamplePath + "cloud.png")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 6 - 22 - 17", PathTestsGroup) {
    val controller = new ConsoleController()
  val outputPath = Paths.get(baseOutputPath + "output.bmp")
    val inputPath = Paths.get(baseSamplePath + "cloud.jpg")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 7 - 23 - 17", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.bmp")
    val inputPath = Paths.get(baseSamplePath + "cloud.gif")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 8 - 24 - 17", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.bmp")
    val inputPath = Paths.get(baseSamplePath + "cloud.bmp")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 5 - 21 - 18", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.jpg")
    val inputPath = Paths.get(baseSamplePath + "cloud.png")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 5 - 21 - 19", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.png")
    val inputPath = Paths.get(baseSamplePath + "cloud.png")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }


  test("TDL=1; path: 1 - 2 - 5 - 21 - 20", PathTestsGroup) {
    val controller = new ConsoleController()
    val outputPath = Paths.get(baseOutputPath + "output.gif")
    val inputPath = Paths.get(baseSamplePath + "cloud.png")
    val args = List("--image", inputPath.toString, "--output-rgb-file", outputPath.toString)
    val consoleView = new ConsoleView(controller, args)
    consoleView.run()

    assert(Files.exists(outputPath))
    Files.deleteIfExists(outputPath)
  }

}
