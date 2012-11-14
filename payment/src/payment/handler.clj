(ns payment.handler
  (:use [compojure.core])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [payment.views :as views]))

(defroutes app-routes
              (POST "/membership/subscribe_rich" 
                      {params :params}
                      "subscribe a new item for an user in the liberECO"
                      (views/subscribe-item params))
              (route/not-found "Hello, Not Found"))

(def app
  (handler/site app-routes))
