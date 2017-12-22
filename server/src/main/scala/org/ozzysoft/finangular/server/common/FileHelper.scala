package org.ozzysoft.finangular.server.common

import java.io._

import grizzled.slf4j.Logger
import org.apache.commons.io.IOUtils

trait FileHelper {

  protected def logger: Logger

  def createDir(basePath: String = ".", dirName: String): File = {
    val dir = new File(basePath, dirName)
    if (!dir.exists()) dir.mkdirs()
    if (!dir.exists()) throw new RuntimeException(s"unable to create directory (${dir.getAbsolutePath})")
    dir
  }

  def getFileBytes(file: File, maybeGatewayid: Option[String] = None) = {
    getInputStreamBytes(file, new BufferedInputStream(new FileInputStream(file)))
  }

  def getInputStreamBytes(file: File, stream: BufferedInputStream) = {
    val requestBytes = IOUtils.toByteArray(stream)
    logger.info(s"file ($file), bytes (${requestBytes.length})")
    requestBytes
  }

  def getFileResourceBytes(file: String) = {
    val inputStream: InputStream = getResource(file)
    val requestBytes = IOUtils.toByteArray(inputStream)
    logger.info(s"file ($file), bytes (${requestBytes.length})")
    requestBytes
  }

  def getResource(path: String) = {
    val inputStream = getClass.getClassLoader.getResourceAsStream(path)
    if (inputStream == null) {
      throw new RuntimeException(s"could not find resource ($path)")
    }
    inputStream
  }

}
