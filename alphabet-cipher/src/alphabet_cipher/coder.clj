(ns alphabet-cipher.coder)

(defn char_to_alphabet [c]
  (- (int c) 97))

(defn alphabet_to_char [a]
  (char (+ a 97)))

(defn encode-one [k m]
  (alphabet_to_char
   (mod
    (+
     (char_to_alphabet k) (char_to_alphabet m)) 26)))
(defn decode-one [k m]
  (alphabet_to_char
   (mod
    (-
     (char_to_alphabet m) (char_to_alphabet k)) 26)))

(defn make_chiper_pair [key message]
  (map vector (cycle key) message))

(defn repeats? [pattern input]
  (empty?
   (filter
    #(not (every? identity (map = pattern %)))
    (partition-all (count pattern) input))))

(defn extract [x]
  (loop [out (str (first x)) r (rest x)]
    (if (empty? r)
      out
      (if (repeats? out r)
        out
        (recur (str out (first r)) (rest r))))))

(defn encode [keyword message]
;; use map with #
  (apply str (map #(apply encode-one %) (make_chiper_pair keyword message))))

(defn decode [keyword message]
;; use map with #
  (apply str (map #(apply decode-one %) (make_chiper_pair keyword message))))

(defn decipher [cipher message]
  (extract (decode message cipher)))

