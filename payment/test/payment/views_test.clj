(ns payment.views-test
  (:use clojure.test
        midje.sweet
        payment.handler
        payment.views))

(deftest post-subscribe-item 
         (testing "post to subscribe an item"
                  (let [request {
                                 :scheme :http
                                 :uri "/membership/subscribe_rich"
                                 :server-name "localhost"
                                 :server-port 3000
                                 :content-type "application/x-www-form-urlencoded"
                                 :request-method :post
                                 :params {
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
                    (is (= (app-routes request) 
                           response)))))

(fact "default domain is given if not present"
      (give-default-domain-id-if-nil nil) => :default-domain-id )
