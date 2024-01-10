package asciiart.image.exporters

import asciiart.image.models.image.RGBImage
import asciiart.image.models.pixel.RGBPixel
import org.mockito.ArgumentMatchersSugar.anyInt
import org.mockito.MockitoSugar
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.FunSuite




class GIFImageExporterTest extends FunSuite with MockitoSugar {

  test("GIFImageExporter should set correct file path and format") {
    val filePath = "test.gif"
    val exporter = GIFImageExporter(filePath)

    assert(exporter.filePath == filePath)
    assert(exporter.exportFormat == "gif")
  }

  test("exportImage should successfully export an image") {
    val filePath = "test.gif"
    val exporter = GIFImageExporter(filePath)

    val mockImage = mock[RGBImage]
    when(mockImage.width).thenReturn(10)
    when(mockImage.height).thenReturn(10)
    when(mockImage.getPixel(anyInt, anyInt)).thenReturn(RGBPixel(255, 255, 255))

    val result = exporter.exportImage(mockImage)

    assert(result.isRight)
  }

  test("exportImage should return error if there is an exception") {
    val filePath = "invalidPath/test.gif"
    val exporter = GIFImageExporter(filePath)

    val mockImage = mock[RGBImage]
    when(mockImage.width).thenReturn(10)
    when(mockImage.height).thenReturn(10)
    when(mockImage.getPixel(anyInt, anyInt)).thenReturn(RGBPixel(255, 255, 255))

    val result = exporter.exportImage(mockImage)

    assert(result.isLeft)
    // The exact error message can depend on the exception thrown
  }
}
