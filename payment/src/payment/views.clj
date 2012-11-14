(ns payment.views)

(defn subscribe-item
  "subscribe an new item"
  [{user-id :USERID
    item-id :ITEMID
    payment-type :PAYMENTTYPE
    domain-id :DOMAINID}]
  (str "welcome? user:" user-id)  
  )

