(ns clj-liblinear.test
  (:use
   clojure.test
   [clj-liblinear.core :only [train predict]]))


(deftest simple-classification
  (let [train-data
        (concat
         (repeatedly 300 #(hash-map :class 0 :f {:x (rand), :y (rand)}))
         (repeatedly 300 #(hash-map :class 1 :f {:x (- (rand)), :y (- (rand))})))
        model (train
               (map :f train-data)
               (map :class train-data)
               :algorithm :l2l2)]
    
    [(is (= 0 (predict model {:x (rand) :y (rand)})))
     (is (= 1 (predict model {:x (- (rand)) :y (- (rand))})))]))