(ns q82)

(defn word-chain?
  [w1 w2]
  (let [ch1 (first w1)
        ch2 (first w2)]
    (cond
     (> (count w1) (count w2)) (recur w2 w1)
     (= ch1 ch2) (recur (next w1) (next w2))
     (= ch1 (second w2)) (= (next w1) (drop 2 w2))
     (= ch2 (second w1)) (= (next w2) (drop 2 w1))
     :else (= (next w1) (next w2)))))



(defn has-word-chain?
  [cur col]
  (loop [e (first col)]
   (if (empty? 
    (if (word-chain? cur e) true
        (recur (next col)))))

(defn word-chain [col]
  (loop [col1 col
         col2 col]
    (if (has-word-chain? (first col1) col2) (recur (next col1) col2 )
        false)))

(= (next "cd") (drop 2 "dcd"))
(has-word-chain? "hat" #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})
(= true (word-chain #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))

(drop 2 "abadc")
(next "abc")


(word-chain? "acd" "adcd")
(word-chain? "adcd" "acd")
(word-chain? "dcd" "cd")
(word-chain? "dc" "cdc")
