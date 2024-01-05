package org.example.ch01_java.ch09_common;

import java.util.*;

/**
 * @author: whtli
 * @date: 2024/01/05
 * @description: for-each循环优先于传统的for循环
 */
public class ForEachBetterThanFor1 {
    static Collection<Suit> suits = Arrays.asList(Suit.values());
    static Collection<Rank> ranks = Arrays.asList(Rank.values());

    public static void main(String[] args) {
        /*List<Card> deck = new ArrayList<>();
        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); ) {
                deck.add(new Card(i.next(), j.next()));
            }
        }
        System.out.println(deck);*/

        // 修正方法1
        List<Card> deck1 = new ArrayList<>();
        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
            Suit suit = i.next();
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); ) {
                deck1.add(new Card(suit, j.next()));
            }
        }
        for (Card card : deck1) {
            System.out.println(card.toString());
        }

        System.out.println("---------------");

        // 修正方法2
        List<Card> deck2 = new ArrayList<>();
        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                deck2.add(new Card(suit, rank));
            }
        }
        for (Card card : deck2) {
            System.out.println(card.toString());
        }
    }
}

enum Suit {CLUB, DIAMOND, HEART, SPADE}

enum Rank {ACE, DEUCE, THREE, DOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

class Card {
    Suit suit;
    Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return suit.toString() + ", " + rank.toString();
    }
}