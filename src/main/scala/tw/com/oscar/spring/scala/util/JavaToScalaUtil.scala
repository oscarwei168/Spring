package tw.com.oscar.spring.scala.util

import scala.collection.JavaConverters

/**
 * <p>
 * Title: JavaToScalaUtil.java<br>
 * </p>
 * <strong>Description:</strong> A scala utilities <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 * ~
 * @author ${USER_NAME}
 * @since 2015/7/29
 * @version v1, 2015/7/29
 * @see https://github.com/wkennedy/swagger4spring-web
 */
object JavaToScalaUtil {

  def toScalaImmutableMap[A, B](javaMap: java.util.Map[A, B]): scala.collection.immutable.Map[A, B] = {
    JavaConverters.mapAsScalaMapConverter(javaMap).asScala.toMap
  }

  def toScalaList[T](javaList: java.util.List[T]): List[T] = {
    if (null != javaList) {
      return scala.collection.JavaConverters.collectionAsScalaIterableConverter(javaList).asScala.toList
    }
    scala.collection.immutable.List.empty
  }
}
