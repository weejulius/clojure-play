(ns payment.test.domain-test
  (:use midje.sweet)) 
(import 'java.util.concurrent.atomic.AtomicInteger)


(def current-payment-id (AtomicInteger. 0))

(defn gen-payment 
  "generate a new payment to track the purcahse procedure of item(s)"
  [{:keys [user,items]}]
  (.incrementAndGet current-payment-id))

(fact (+ 1 1) => even?)

(fact "In order to make user complete a new payment on the provider
      As a community I want to get an unique id for the payment
      So that the provider can create payment page according to the id for the user"
      (gen-payment {:user 123 :items '(1)}) => (roughly 1)
      (gen-payment {:user 124 :items '(1)}) => (roughly 2))

