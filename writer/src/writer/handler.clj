(ns writer.handler
  (:use [compojure.core])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [writer.views :as views]))

(defroutes app-routes
              (POST "/notes" 
                      {params :params}
                      "add a new note""
                      (views/create-new-note params))
              (route/not-found "Hello, Not Found"))

(def app
  (handler/site app-routes))
