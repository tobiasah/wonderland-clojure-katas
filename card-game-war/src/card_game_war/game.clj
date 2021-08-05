(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn play-round [player1-card player2-card]
  (if (= player1-card player2-card)
    -1
    (if (= (.indexOf suits (.get player1-card 0)) (.indexOf suits  (.get player2-card 0)))
      (if (> (.indexOf ranks (.get player1-card 1)) (.indexOf ranks (.get player2-card 1)))
        1
        2)
      (if (< (.indexOf suits (.get player1-card 0)) (.indexOf suits  (.get player2-card 0)))
        1
        2))))

(defn win-round [winner-stack looser-card stack]

  (concat (rest winner-stack) stack [(first winner-stack)] [looser-card]))

(defn init-game []
  [(shuffle cards) (shuffle cards)])

(defn play-game [player1-cards player2-cards]
  (loop [p1 player1-cards p2 player2-cards stack []]
  (prn p1)
  (prn p2)
  (prn stack)
  (if (some empty? [p1 p2])
    (if (empty? p1)
      2
      1
    )
    (case (play-round (first p1) (first p2))
      -1 (recur (rest p1) (rest p2) (concat stack [(first p1)] [(first p2)]))
      1  (recur (win-round p1 (first p2) stack) (rest p2) [])
      2  (recur (rest p1) (win-round p2 (first p1) stack) [])))))