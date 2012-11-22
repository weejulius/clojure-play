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
  (if(blank? domain-name) default-domain-id
    (let[domain-id (keyword domain-name) valid-domain-id (some #{domain-id} domain-ids)]
      (if(nil? valid-domain-id) 
        (throw (IllegalArgumentException. (str "the domain '" domain-name "' is not existing")))
        valid-domain-id))))

(fact "fetch domain id by name"
      (fetch-domain-id-by-name domain-ids "poppen" :poppen) => :poppen
      (fetch-domain-id-by-name domain-ids "gays" :poppen) => :gays
      (fetch-domain-id-by-name domain-ids "other" :poppen) =>
       (throws IllegalArgumentException "the domain 'other' is not existing") 
      (fetch-domain-id-by-name domain-ids "" :a) => :a
      (fetch-domain-id-by-name domain-ids nil :abc) => :abc)
