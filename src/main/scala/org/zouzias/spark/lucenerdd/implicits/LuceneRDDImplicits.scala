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

package org.zouzias.spark.lucenerdd.implicits

import org.apache.lucene.document._
import org.zouzias.spark.lucenerdd.models.LuceneText

import scala.reflect.ClassTag

/**
 * Implicit conversions of basic types to Apache Lucene documents
 *
 * Currently supports:
 * 1) Primitive types: Int, Long, Float, Double, String and [[LuceneText]]
 * 2) Tuples up to size 7 of the above types
 */
object LuceneRDDImplicits {

  val Stored = Field.Store.YES

  implicit def intToDocument(v: Int): Document = {
    val doc = new Document
    doc.add(new IntField("_1", v, Stored))
    doc
  }

  implicit def longToDocument(v: Long): Document = {
    val doc = new Document
    doc.add(new LongField("_1", v, Stored))
    doc
  }

  implicit def doubleToDocument(v: Double): Document = {
    val doc = new Document
    doc.add(new DoubleField("_1", v, Stored))
    doc
  }

  implicit def floatToDocument(v: Float): Document = {
    val doc = new Document
    doc.add(new FloatField("_1", v, Stored))
    doc
  }

  implicit def stringToDocument(s: String): Document = {
    val doc = new Document
    doc.add(new StringField("_1", s, Stored))
    doc
  }

  implicit def textFieldToDocument(s: LuceneText): Document = {
    val doc = new Document
    doc.add(new TextField("_1", s.content, Stored))
    doc
  }

  private def tupleTypeToDocument[T: ClassTag](doc: Document, index: Int, s: T): Document = {
   typeToDocument(doc, s"_${index}", s)
  }

  private def typeToDocument[T: ClassTag](doc: Document, fieldName: String, s: T): Document = {
    s match {
      case x: LuceneText =>
        doc.add(new TextField(fieldName, x.content, Stored))
      case x: String =>
        doc.add(new StringField(fieldName, x, Stored))
      case x: Int =>
        doc.add(new IntField(fieldName, x, Stored))
      case x: Double =>
        doc.add(new DoubleField(fieldName, x, Stored))
      case x: Float =>
        doc.add(new FloatField(fieldName, x, Stored))
      case x: Long =>
        doc.add(new LongField(fieldName, x, Stored))
    }

    doc
  }


  implicit def mapToDocument[T: ClassTag](map: Map[String, T]): Document = {
    val doc = new Document
    map.keys.foreach{ case key =>
      typeToDocument(doc, key, map.get(key).get)
    }
    doc
  }

  implicit def tuple2ToDocument[T1: ClassTag, T2: ClassTag](s: (T1, T2)): Document = {
    val doc = new Document
    tupleTypeToDocument[T1](doc, 1, s._1)
    tupleTypeToDocument[T2](doc, 2, s._2)
    doc
  }

  implicit def tuple3ToDocument[T1: ClassTag,
  T2: ClassTag,
  T3: ClassTag](s: (T1, T2, T3)): Document = {
    val doc = new Document
    tupleTypeToDocument[T1](doc, 1, s._1)
    tupleTypeToDocument[T2](doc, 2, s._2)
    tupleTypeToDocument[T3](doc, 3, s._3)
    doc
  }

  implicit def tuple4ToDocument[T1: ClassTag,
  T2: ClassTag,
  T3: ClassTag,
  T4: ClassTag](s: (T1, T2, T3, T4)): Document = {
    val doc = new Document
    tupleTypeToDocument[T1](doc, 1, s._1)
    tupleTypeToDocument[T2](doc, 2, s._2)
    tupleTypeToDocument[T3](doc, 3, s._3)
    tupleTypeToDocument[T4](doc, 4, s._4)
    doc
  }

  implicit def tuple5ToDocument[T1: ClassTag,
  T2: ClassTag,
  T3: ClassTag,
  T4: ClassTag,
  T5: ClassTag](s: (T1, T2, T3, T4, T5)): Document = {
    val doc = new Document
    tupleTypeToDocument[T1](doc, 1, s._1)
    tupleTypeToDocument[T2](doc, 2, s._2)
    tupleTypeToDocument[T3](doc, 3, s._3)
    tupleTypeToDocument[T4](doc, 4, s._4)
    tupleTypeToDocument[T5](doc, 5, s._5)
    doc
  }

  implicit def tuple6ToDocument[T1: ClassTag,
  T2: ClassTag,
  T3: ClassTag,
  T4: ClassTag,
  T5: ClassTag,
  T6: ClassTag](s: (T1, T2, T3, T4, T5, T6)): Document = {
    val doc = new Document
    tupleTypeToDocument[T1](doc, 1, s._1)
    tupleTypeToDocument[T2](doc, 2, s._2)
    tupleTypeToDocument[T3](doc, 3, s._3)
    tupleTypeToDocument[T4](doc, 4, s._4)
    tupleTypeToDocument[T5](doc, 5, s._5)
    tupleTypeToDocument[T6](doc, 6, s._6)
    doc
  }

  implicit def tuple7ToDocument[T1: ClassTag,
  T2: ClassTag,
  T3: ClassTag,
  T4: ClassTag,
  T5: ClassTag,
  T6: ClassTag,
  T7: ClassTag](s: (T1, T2, T3, T4, T5, T6, T7)): Document = {
    val doc = new Document
    tupleTypeToDocument[T1](doc, 1, s._1)
    tupleTypeToDocument[T2](doc, 2, s._2)
    tupleTypeToDocument[T3](doc, 3, s._3)
    tupleTypeToDocument[T4](doc, 4, s._4)
    tupleTypeToDocument[T5](doc, 5, s._5)
    tupleTypeToDocument[T6](doc, 6, s._6)
    tupleTypeToDocument[T7](doc, 7, s._7)
    doc
  }

}