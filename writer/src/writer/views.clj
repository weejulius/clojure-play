
(ns writer.views
  (:use [midje.sweet])
  (:import [java.lang.IllegalArgumentException]))


(defn create-new-note
  "create a new note"
  [{user-id :USERID}]
  (str "welcome? user:" user-id))

