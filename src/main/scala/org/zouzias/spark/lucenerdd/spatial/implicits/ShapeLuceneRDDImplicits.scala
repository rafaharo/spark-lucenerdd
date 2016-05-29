/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zouzias.spark.lucenerdd.spatial.implicits


import com.spatial4j.core.shape.Shape
import org.zouzias.spark.lucenerdd.spatial.ContextLoader


object ShapeLuceneRDDImplicits extends ContextLoader{

  implicit def convertToPoint(point: (Double, Double)): Shape = {
    ctx.makePoint(point._1, point._2)
  }

  implicit def rectangleToShape(rect: (Double, Double, Double, Double)): Shape = {
    val minX = rect._1
    val maxX = rect._2
    val minY = rect._3
    val maxY = rect._4

    ctx.makeRectangle(minX, maxX, minY, maxY)
  }

  implicit def circleToShape(circle: ((Double, Double), Double)): Shape = {
    val x = circle._1._1
    val y = circle._1._2
    val radius = circle._2

    ctx.makeCircle(x, y, radius)
  }
}
