(ns payment.views
  (:use [midje.sweet]
        [clojure.string :only (blank?)]))

(def default-domain-id :poppen)
(def domain-ids [:poppen :kauf :gays])

(defn subscribe-item
  "subscribe a new item"
  [{user-id :USERID
    item-id :ITEMID
    payment-type :PAYMENTTYPE
    domain-id :DOMAINID}]
  (str "welcome? user:" user-id))

(defn give-default-domain-id-if-nil
  "give the default domain id if not present"
  ([]
   (give-default-domain-id-if-nil :poppen))
  ([default-domain-id]
   #(if(blank? %) default-domain-id (keyword %)))) 

(defn validate-domain-id?
  "valid domain id if the domain id is found from the pre-defined domain ids,
  note: a default domain id is given if the domain id is not present"
  ([domain-ids domain-id]
   (validate-domain-id? domain-ids domain-id nil))

  ([domain-ids domain-id give-default-domain]
   (in? domain-ids ((fnil apply keyword [domain-id]) give-default-domain [domain-id]))))

(fact "default domain is given if not present"
      ((give-default-domain-id-if-nil) nil) => :poppen
      (let [give-default-domain (give-default-domain-id-if-nil :default-domain-id)] 
        (give-default-domain nil) => :default-domain-id 
        (give-default-domain "a") =>  :a
        (give-default-domain "") => :default-domain-id))

(fact "is a valid domain id"
      (validate-domain-id? domain-ids nil) => true
      (validate-domain-id? domain-ids nil (give-default-domain-id-if-nil)) => true
      (validate-domain-id? domain-ids "a" (give-default-domain-id-if-nil)) => false
      (validate-domain-id? domain-ids "gays" (give-default-domain-id-if-nil)) => true)

(fact "is element in the seq"
      (in? [1 2 3] 1) => true
      (in? [1 2 3] 4) => false)

(defn in?
  "true if the element is in the seq"
  [seq elm]
  (if(some #(= elm %) seq) true false)) 
