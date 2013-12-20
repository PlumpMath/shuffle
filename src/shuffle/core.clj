(ns shuffle.core
  (:require  [clojure.math.numeric-tower :as math]))

(defn abs [x] 
  (if (< x 0) (- x) x))

(defn lrc [cut deck]
  "remaining cards"
  (- (count deck) (* 2 cut)))

(defn dominate [cut deck]
  "returns the dominate side of the deck"
  (case (compare (lrc cut deck) 0) 
    -1 :top
     0 :even
     1 :bottom))

(defn remaining-cards [cut deck]
  "returns the number of cards that are left out by the cut"
  (abs (lrc cut deck)))

(defn cut-size [cut deck]
  "returns the number of cards in each side of the cut"
  (/ (- (count deck) (remaining-cards cut deck)) 2))


(defmulti remain dominate)

(defmethod remain :top [cut deck] 
  (take (remaining-cards cut deck) deck))

(defmethod remain :even [cut deck] []) 

(defmethod remain :bottom [cut deck] 
   (take (remaining-cards cut deck) 
         (drop  (cut-size cut deck) deck)))


(defmulti active dominate)

(defmethod active :top [cut deck]
  (vec (flatten (drop (remaining-cards cut deck) deck))))

(defmethod active :even [cut deck] deck)

(defmethod active :bottom [cut deck]
   (vec ( flatten (conj 
         (vec (take (cut-size cut deck) deck))
         (vec (drop (remaining-cards cut deck)
                    (drop (cut-size cut deck) deck)))))))

(defn shuf [cut deck]
  (let [cut-count (cut-size cut deck)
        top       (take cut-count (active cut deck))  
        bottom    (drop cut-count (active cut deck))
        remainder (vec (remain cut deck))]
        (vec 
          (flatten 
            (conj remainder (vec (interleave bottom top)))))))

(def shuf-memo (memoize shuf))
 
(defn shuf-sort [cut init deck]
  (loop [deck deck cnt init]
    (if (= deck (sort deck))
      cnt
      (recur (shuf-memo cut deck) (inc cnt)))))

(defn shuf-list [cut init deck]
  (loop [deck deck cnt init accum (range (count deck))]
    (if (= deck (sort deck))
      accum 
      (recur 
        (shuf-memo cut deck) 
        (inc cnt) 
        (map (fn [coll x] (list coll x) ) accum deck)))))

(defn shuf-times [cut init times deck]
 (loop [deck deck 
        cnt init 
        accum (range (count deck))]
    (if (= times cnt)
      accum 
      (recur 
        (shuf-memo cut deck) 
        (inc cnt) 
        (map (fn [coll x] (list coll x) ) accum deck)))) 

 )
(defn shuf-sets [cut len times]
(set (map  (comp set flatten)  
       (shuf-times cut 1 times (shuf cut  (range len))))))


(defn shuf-stats [cut len]
  (let [cycles  (shuf-sets cut len len)
        factors (map count cycles)
        times   (reduce math/lcm (flatten factors))]
  {:cycles  cycles,
   :factors factors,
   :times   times,
   :len     len,
   :cut     cut}))
