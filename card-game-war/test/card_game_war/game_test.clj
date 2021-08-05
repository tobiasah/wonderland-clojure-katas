(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest suit wins the cards in the round"
    (is (= 1 (play-round [:spade 2] [:heart 2]))))
  (testing "queens are higher rank than jacks"
    (is (= 1 (play-round [:spade :queen] [:spade :jack]))))
  (testing "kings are higher rank than queens"
    (is (= 1 (play-round [:spade :king] [:spade :queen]))))
  (testing "aces are higher rank than kings"
    (is (= 1 (play-round [:spade :ace] [:spade :king])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= 1 (play-game [[:spade 2][:club 3]] [[:club 2] [:spade 1]]))))
  (testing "a complete game returns 1 or 2"
      (is (some  #(= (apply play-game (init-game)) %) [1 2]))))

(deftest test-init-game
  (testing "the players have different cards"
    (is (apply not= (init-game))))
  (testing "the cards are shuffled"
      (is (not= cards (first (init-game))))))
