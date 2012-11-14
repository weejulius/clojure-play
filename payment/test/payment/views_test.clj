(ns payment.views-test
  (:use clojure.test))

(deftest post-subscribe-item 
         (testing "post to subscribe an item"
                  (let [request {
                                 :uri "/membership/subscribe_rich"
                                 :server-name "localhost"
                                 :server-port 3000
                                 :content-type "application/x-www-form-urlencoded"
                                 :request-method :post
                                 :parameters {
                                              :USERID "123"
                                              :ITEMID "1"
                                              :DOMAINID "d1"
                                              :PAYMENTTYPE "dd"
                                              :OTHERS "hello world"
                                              }}
                        response {
                                  :status 200
                                  :body "1"
                                  }]
                    (is (= (ring-app request) 
                           response)))))
