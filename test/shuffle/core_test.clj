(ns shuffle.core-test
  (:require [clojure.test :refer :all]
            [shuffle.core :refer :all]))

(def d5 [1 2 3 4 5])
(def d8 [1 2 3 4 5 6 7 8])

(deftest cut-size-test
   (is (= 2 (cut-size 3 d5)))
   (is (= 2 (cut-size 2 d5)))
   (is (= 1 (cut-size 4 d5))))

(deftest remaining-cards-test
  (is (= 1  (remaining-cards 3 d5)))
  (is (= 3  (remaining-cards 1 d5))))

(deftest remain-test
  (is (= [3] (remain 2 d5)))
  (is (= [1] (remain 3 d5)))
  (is (= []  (remain 2 [1 2 3 4]))))

(deftest shuf-test
  (is (= [1 4 2 5 3]) (shuf 3 d5))
  (is (= [3 4 1 5 2]) (shuf 2 d5)))
