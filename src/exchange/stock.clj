(ns exchange.stock)

(defn to-string
  "converts a stock to a string"
  [stock]
  (str (stock :name) " (" (stock :symbol) ")"))

(defn print-stocks
  "prints a list of stocks"
  [stocks]
  (println "There are " (count stocks) " stocks")
  (doseq [stock stocks]
    (println (to-string stock))))

(defn add
  "adds a stock to the provided map, keyed by :symbol"
  [stocks stock]
  (dosync
    (alter stocks assoc (stock :symbol), stock)))

(defn calculate-new-volume
  [stock quantity]
  (+ (get @stock :volume 0) quantity)
  )

(defn trade
  "execute a trade of stock from one party to another"
  [stock quantity price]
  (dosync
    (alter stock assoc :price, price)
    (alter stock assoc :volume, (calculate-new-volume stock quantity))
    )
  (println "Traded " quantity (stock :symbol) " at " price)
  )
