(ns exchange.test.stock
  (:use [exchange.stock :as stock])
  (:use [clojure.test]))

(deftest test-to-string
  (is (= "Google (GOOG)" (stock/to-string (ref {:symbol "GOOG", :name "Google"}))))
  )

(deftest test-add
  (def stocks (ref {}))
  (def stock (ref {:symbol "GOOG", :name "Google"}))
  (is (= 0 (count @stocks)))
  (stock/add stocks stock)
  (is (= 1 (count @stocks)))
  (is (= stock (get @stocks "GOOG")))
  )

(deftest test-trade-updates-stock-with-details-of-trade
  (def stock (ref {:symbol "GOOG", :name "Google", :price 524.84, :volume 100}))
  (def quantity 300)
  (def price 525.00)
  (stock/trade stock quantity price)
  (is (= price (stock :price)))
  (is (= 400 (stock :volume)))
  )

(deftest test-calculate-new-volume
  (is (= 7 (stock/calculate-new-volume (ref {}) 7)))
  (is (= 142 (stock/calculate-new-volume (ref {:volume 0}) 142)))
  (is (= 200 (stock/calculate-new-volume (ref {:volume 100}) 100)))
  )