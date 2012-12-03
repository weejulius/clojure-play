(ns payment.views
  (:use [midje.sweet]
        [clojure.string :only (blank?)])
  (:import [java.lang.IllegalArgumentException]))

(def default-domain-id :poppen)
(def domain-ids [:poppen :kauf :gays])

(defn subscribe-item
  "subscribe a new item"
  [{user-id :USERID
    item-id :ITEMID
    payment-type :PAYMENTTYPE
    domain-id :DOMAINID}]
  (str "welcome? user:" user-id))

(defn fetch-domain-id-by-name
  "get the valid domain id according the name passed in"
  [domain-ids domain-name default-domain-id]
  (let [domain-id (with-default #(blank? %) (keyword domain-name) default-domain-id)
        ))
(defn with-default
  "return default value if the predicate is qualified"
  ([pred value default-value map-fn]
  (if(pred value) default-value (map-fn value)))
  ([pred value default-value]
     (with-default pred value default identity))))
(fact "set default value"
      (with-default nil? "1" "default") => "1"
      (with-default nil? nil "default") => "default"
      (with-default #(> % 1) 1 0) => 1
      (with-default #(> % 1) 3 0) => 0)

(fact "fetch domain id by name"
      (fetch-domain-id-by-name domain-ids "poppen" :poppen) => :poppen
      (fetch-domain-id-by-name domain-ids "gays" :poppen) => :gays
      (fetch-domain-id-by-name domain-ids "other" :poppen) =>
       (throws IllegalArgumentException "the domain 'other' is not existing")
      (fetch-domain-id-by-name domain-ids "" :a) => :a
      (fetch-domain-id-by-name domain-ids nil :abc) => :abc)
