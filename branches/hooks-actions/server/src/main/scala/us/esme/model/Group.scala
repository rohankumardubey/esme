package us.esme.model

/*
 * Copyright 2008 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */


import net.liftweb._
import mapper._
import util._

object Group extends Group with LongKeyedMetaMapper[Group] {
  override def dbTableName = "a_group" // define the DB table name

  def findGroup(name: String): Box[Group] = find(By(this.name, name))
}

class Group extends LongKeyedMapper[Group] {
  def getSingleton = Group // what's the "meta" server
  def primaryKeyField = id

  object id extends MappedLongIndex(this)

  object name extends MappedPoliteString(this, 256) {
    override def validations =
    this.valMinLen(3, "The minimum group length is 3 characters") _ ::
    super.validations

  override def setFilter = trim _ :: Helpers.capify _ :: super.setFilter

  override def dbIndexed_? = true
  }
}