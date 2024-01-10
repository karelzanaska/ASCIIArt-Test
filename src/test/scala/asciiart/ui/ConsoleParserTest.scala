package asciiart.ui

import asciiart.image.convertors.image.{GrayscaleToAsciiImageConverter, PatternBasedAsciiImageConverter}
import asciiart.image.convertors.pixel.{NonLinearGrayscaleToAsciiPixelConvertor, PaulBourkeGrayscaleToAsciiPixelConvertor}
import asciiart.image.exporters.{StdoutImageExporter, TxtImageExporter}
import asciiart.image.importers.{FileSystemImageImporter, JPGImageImporter, RandomImageGeneratorImporter}
import org.scalatest.FunSuite

class ConsoleParserTest extends FunSuite {

  test("isHelp should return true when h flag is passed") {
    val args = List("--image", "whateverimage.jpg", "--h")
    val result = new ConsoleParser(args).isHelp()
    assert(result)
  }

  test("isHelp should return true when help flag is passed") {
    val args = List("--image", "whateverimage.jpg", "--help")
    val result = new ConsoleParser(args).isHelp()
    assert(result)
  }

  test("isHelp should return false when no help flag is passed") {
    val args = List("--image", "whateverimage.jpg")
    val result = new ConsoleParser(args).isHelp()
    assert(!result)
  }

  test("getImageImporter should return JPGImageImporter for valid JPG image path") {
    val parser = new ConsoleParser(List("--image", "test.jpg"))
    val expected = Right(JPGImageImporter("test.jpg"))
    assert(parser.getImageImporter() == expected)
  }

  test("getImageImporter should return PNGImageImporter for valid PNG image path") {
    val parser = new ConsoleParser(List("--image", "test.png"))
    val expected = Right(JPGImageImporter("test.png"))
    assert(parser.getImageImporter() == expected)
  }

  test("getImageImporter should return GIFImageImporter for valid GIF image path") {
    val parser = new ConsoleParser(List("--image", "test.gif"))
    val expected = Right(JPGImageImporter("test.gif"))
    assert(parser.getImageImporter() == expected)
  }

  test("getImageImporter should return BMPImageImporter for valid BMP image path") {
    val parser = new ConsoleParser(List("--image", "test.bmp"))
    val expected = Right(JPGImageImporter("test.bmp"))
    assert(parser.getImageImporter() == expected)
  }

  test("getImageImporter should return RandomImageGeneratorImporter for image-random command") {
    val parser = new ConsoleParser(List("--image-random"))
    val expected = Right(RandomImageGeneratorImporter())
    assert(parser.getImageImporter() == expected)
  }

  test("getImageImporter should return Unsupported image format error for unsupported image format") {
    val parser = new ConsoleParser(List("--image", "test.bxp"))
    val expected = Left("Unsupported image format. Please use JPG or PNG or GIF or BMP file.")
    assert(parser.getImageImporter() == expected)
  }

  test("getImageExporters should return StdoutImageExporter for output-console command") {
    val parser = new ConsoleParser(List("--output-console"))
    val expected = Right(List(StdoutImageExporter()))
    assert(parser.getAsciiImageExporters() == expected)
  }

  test("getImageExporters should return TxtImageExporter for valid TXT file path") {
    val parser = new ConsoleParser(List("--output-file", "test.txt"))
    val expected = Right(List(TxtImageExporter("test.txt")))
    assert(parser.getAsciiImageExporters() == expected)
  }

  test("ConsoleParser should return Unsupported image format error when image path is not JPG or PNG file") {
    val consoleParser = new ConsoleParser(List("--image", "test.glf"))
    assert(consoleParser.getImageImporter() == Left("Unsupported image format. Please use JPG or PNG or GIF or BMP file."))
  }

  test("ConsoleParser should return Multiple image commands specified error when multiple image commands are specified") {
    val consoleParser = new ConsoleParser(List("--image", "test.jpg", "--image-random"))
    assert(consoleParser.getImageImporter() == Left("Multiple image commands specified. Please specify only one image command."))
  }

  test("ConsoleParser should return Unsupported image format error when output file path is not TXT file") {
    val consoleParser = new ConsoleParser(List("--output-file", "test.doc"))
    assert(consoleParser.getAsciiImageExporters() == Left("Unsupported output file format. Please use TXT file."))
  }

  test("ConsoleParser should return Invalid argument for rotate error when rotate filter argument is not valid number") {
    val consoleParser = new ConsoleParser(List("--rotate", "invalid"))
    assert(consoleParser.getImageFilters() == Left("Invalid argument for rotate."))
  }

  test("ConsoleParser should return Invalid argument for scale error when scale filter argument is not valid number") {
    val consoleParser = new ConsoleParser(List("--scale", "invalid"))
    assert(consoleParser.getImageFilters() == Left("Invalid argument for scale."))
  }

  test("ConsoleParser should return Invalid argument for flip error when flip filter argument is not horizontal or vertical") {
    val consoleParser = new ConsoleParser(List("--flip", "invalid"))
    assert(consoleParser.getImageFilters() == Left("Invalid argument for flip."))
  }

  test("ConsoleParser should return Invalid argument for table error when table convertor argument is not paulbourke or nonlinear") {
    val consoleParser = new ConsoleParser(List("--table", "invalid"))
    assert(consoleParser.getAsciiImageConvertor() == Left("Invalid argument for table. Please use paulbourke or nonlinear."))
  }

  test("ConsoleParser should return Invalid argument for custom-table error when custom table convertor argument is not valid") {
    val consoleParser = new ConsoleParser(List("--custom-table"))
    assert(consoleParser.getAsciiImageConvertor() == Left("Invalid argument for custom-table."))
  }

  test("getImageImporter should return error when no image path is specified") {
    val parser = new ConsoleParser(List("--image"))
    val expected = Left("No image path specified.")
    assert(parser.getImageImporter() == expected)
  }

  test("getAsciiImageConvertor should return PaulBourkeGrayscaleToAsciiPixelConvertor for paulbourke command") {
    val parser = new ConsoleParser(List("--table", "paulbourke"))
    val expected = Right(GrayscaleToAsciiImageConverter(new PaulBourkeGrayscaleToAsciiPixelConvertor))
    assert(parser.getAsciiImageConvertor() == expected)
  }

  test("getAsciiImageConvertor should return NonLinearGrayscaleToAsciiPixelConvertor for nonlinear command") {
    val parser = new ConsoleParser(List("--table", "nonlinear"))
    val expected = Right(GrayscaleToAsciiImageConverter(new NonLinearGrayscaleToAsciiPixelConvertor))
    assert(parser.getAsciiImageConvertor() == expected)
  }

  test("getAsciiImageConvertor should return PatternBasedAsciiImageConverter for patternbased command") {
    val parser = new ConsoleParser(List("--table", "patternbased"))
    val expected = Right(PatternBasedAsciiImageConverter())
    assert(parser.getAsciiImageConvertor() == expected)
  }

}
