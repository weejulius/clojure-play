(ns payment.handler
  (:require [compojure.core :as rq]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [payment.views :as views]))

(rq/defroutes app-routes
  (rq/GET "/membership/subscribe_rich" 
       {params :params :as request}
        ;;"subscribe a new item for an user in the liberECO"
        (println (get-in request [:params]))    
        (views/subscribe-item params))
  (route/not-found "I am Not Found"))

(def app
  (handler/site app-routes))
