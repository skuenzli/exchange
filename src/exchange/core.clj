(ns exchange.core
  (require [exchange.stock :as stock])
  )

(def stocks (ref {}))

(println "Populating exchange")

(stock/add stocks (ref {:symbol "GOOG", :name "Google", :price 526.86}))
(stock/add stocks (ref {:symbol "MSFT", :name "Microsoft", :price 25.25}))

(stock/print-stocks (vals @stocks))

(println "Startup complete\n\n")

(stock/trade (get @stocks "GOOG") 100 570.00)
(println "Current price of GOOG:" ((get @stocks "GOOG") :price))
