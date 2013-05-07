(ns q82)

(defn word-chain?
  [w1 w2]
  (let [ch1 (first w1)
        ch2 (first w2)]
    (cond
     (> (count w1) (count w2)) (recur w2 w1)
     (= ch1 ch2) (recur (rest w1) (rest w2))
     (= ch1 (second w2)) (= (rest w1) (drop 2 w2))
     (= ch2 (second w1)) (= (rest w2) (drop 2 w1))
     :else (= (rest w1) (rest w2)))))



(defn has-word-chain?
  [cur col]
  (loop [col1 col]
   (println (first col1)  "cur:" cur) 
   (if (empty? col1) false
    (if (word-chain? cur (first col1)) true
        (recur (next col))))))

(defn word-chain [col]
  (loop [col1 col]
    (println "col" col1 col)
   (if (has-word-chain? (first col1) col) (recur (rest col1))
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
