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
  "return the valid domain id according the name passed in otherwise throw exception"
  [domain-ids domain-name default-domain-id]
  (let [[domain-id is-default] (with-default #(blank? %) domain-name default-domain-id keyword)
        valid-domain-id (find-item domain-id domain-ids)]
    (if(nil? valid-domain-id)
      (throw (IllegalArgumentException.
              (if(true? is-default) (str "the default domain '" (name default-domain-id) "' is not existing")
                 (str "the domain '" domain-name "' is not existing"))))
      valid-domain-id)))


(defn with-default
  "return default value if the predicate is qualified"
  ([pred value default-value map-fn]
     (if(pred value) [default-value true] [(map-fn value) false]))
  ([pred value default-value]
     (with-default pred value default-value identity)))

(defn find-item
  "find the item in list or set, nil if it is not existing"
  [item coll]
  (some #{item} coll))

(fact "test find item in the list"
      (find-item :poppen domain-ids) => :poppen
      (find-item :other domain-ids) => nil)



(fact "set default value"
      (with-default nil? "1" "default") => ["1" false]
      (with-default nil? nil "default") => ["default" true]
      (with-default #(> % 1) 1 0) => [1 false]
      (with-default #(> % 1) 3 0) => [0 true]
      (with-default #(> % 1) 3 :0  (comp keyword str)) => [:0 true]
      (with-default #(> % 1) 1 :0 (comp keyword str)) => [:1 false]
      (with-default #(blank? %) "" :a) => [:a true])


(fact "fetch domain id by name"
      (fetch-domain-id-by-name domain-ids "poppen" :poppen) => :poppen
      (fetch-domain-id-by-name domain-ids "gays" :poppen) => :gays
      (fetch-domain-id-by-name domain-ids "other" :poppen) =>
      (throws IllegalArgumentException "the domain 'other' is not existing")
      (fetch-domain-id-by-name domain-ids "" :other) =>
      (throws IllegalArgumentException "the default domain 'other' is not existing")
      (fetch-domain-id-by-name domain-ids "" :gays) => :gays
      (fetch-domain-id-by-name domain-ids nil :kauf) => :kauf)
