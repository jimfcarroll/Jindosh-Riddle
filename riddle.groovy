import java.math.BigInteger;

String[] riddlePeople = new String[5];
String[] riddleColors = new String[5];
String[] riddleItems = new String[5];
String[] riddlePlaces = new String[5];
String[] riddleDrinks = new String[5];

// who wore the hat, and what jaundy color was it
riddlePeople[PERSON.contee.ordinal()] = "Natsiou";
riddleColors[COLOR.blue.ordinal()] = "green";

// Who was at the left and what color was the person next to the wearing
riddlePeople[PERSON.marcolla.ordinal()] = "Winslow";
riddleColors[COLOR.purple.ordinal()] = "purple";

// What color was the person who sat left of another person wearing what other color. 
riddleColors[COLOR.red.ordinal()] = "red";
riddleColors[COLOR.white.ordinal()] = "white";

// And what was that first person drinking that they spilled
riddleDrinks[DRINK.wine.ordinal()] = "absinthe";

// Where was the traveler from and what color were they wearing
riddlePlaces[HOME.Dunwall.ordinal()] = "Fraeport";
riddleColors[COLOR.green.ordinal()] = "blue";

// What item was bragged about
riddleItems[HL.tin.ordinal()] = "War Medal";

// Who showed off their heirloom and what was it
riddlePeople[PERSON.winslow.ordinal()] = "Contee";
riddleItems[HL.diamond.ordinal()] = "Bird Pendant";

// Where was the person from who scoffed and what was it no match for
riddlePlaces[HOME.Fraeport.ordinal()] = "Dunwall";
riddleItems[HL.medal.ordinal()] = "Snuff Tin";

// What else was carried where the person next to them (where were they from)  almost spliied their neigbor's drink (what drink)
riddleItems[HL.ring.ordinal()] = "Diamond";
riddlePlaces[HOME.Dab.ordinal()] = "Karnaca";
riddleDrinks[DRINK.beer.ordinal()] = "wine";

// who raised what drink in toast
riddlePeople[PERSON.nat.ordinal()] = "Marcolla";
riddleDrinks[DRINK.whiskey.ordinal()] = "whiskey";

// where was the woman from that jumped up on the table
riddlePlaces[HOME.Baelton.ordinal()] = "Dabokva";

// and what was she drinking
riddleDrinks[DRINK.absinth.ordinal()] = "rum";

// what was the person in the center drinking
riddleDrinks[DRINK.rum.ordinal()] = "beer";

// who captivated them with a story of what place
riddlePeople[PERSON.fitch.ordinal()] = "Finch";
riddlePlaces[HOME.Karnaka.ordinal()] = "Baelton";

// What item is listed that wasn't metioned
riddleItems[HL.pendant.ordinal()] = "Ring";

printEntries = {
    Entry[] entries ->
    println "{ ";
    for (Entry it : entries) {
        println ("  {" + riddlePeople[it.person.ordinal()] + " has " + riddleItems[it.hl.ordinal()]  + "\t\tFrom " + riddlePlaces[it.home.ordinal()] + " wearing " + riddleColors[it.color.ordinal()] + " drinking " + riddleDrinks[it.drink.ordinal()] + "}")
    }
    println "}";
}

enum POS {
     FIRST,
     SECOND,
     THIRD,
     FOUTH,
     FIFTH
}

enum PERSON {
     contee,
     marcolla,
     winslow,
     nat,
     fitch
}

enum COLOR {
     red,
     green,
     blue,
     purple,
     white
}

enum HL {
     diamond,
     medal,
     pendant,
     tin,
     ring
}

enum HOME {
     Dunwall,
     Fraeport,
     Karnaka,
     Baelton,
     Dab
}

enum DRINK {
     beer,
     wine,
     whiskey,
     absinth,
     rum
}

class Entry {
      POS pos
      PERSON person
      COLOR color
      DRINK drink
      HOME home
      HL hl

      Entry(POS pos, PERSON person, COLOR color, DRINK drink = null, HOME home = null, HL hl = null) {
          this.pos = pos
          this.person = person
          this.hl = hl
          this.color = color
          this.home = home
          this.drink = drink
      }

    public String toString() {
        "{" + riddlePeople[person.ordinal()] + " from " + riddleHome[home.ordinal()] + " has " + riddleItems[hl.ordinal()] + " wearing " + riddleColors[color.ordinal()] + " drinking " + riddleDrink[drink.ordinal()] + "}"
    }
}

List<Integer[]> permus = new ArrayList<Integer[]>();
permutations(permus,new HashSet<Integer>([0,1,2,3,4]), new Stack<Integer>(), 5)

def rules = [
             // tin next to Dunwall
             { Entry[] cur -> 
               for (int i = 1; i < cur.length - 1; i++) {
                   if (cur[i].hl == HL.tin) {
                       return (cur[i - 1].home == HOME.Dunwall || cur[i + 1].home == HOME.Dunwall);
                   } else if (cur[i].home == HOME.Dunwall) {
                       return (cur[i - 1].hl == HL.tin || cur[i + 1].hl == HL.tin);
                   }
               }
               return false
             },
             // Winslow has Diamond
             { Entry[] cur -> 
               for (int i = 0; i < cur.length; i++) {
                   if (cur[i].person == PERSON.winslow) {
                       return (cur[i].hl == HL.diamond);
                   } 
               }
               return false
             },
             // The medal is in Fraeport
             { Entry[] cur -> 
               for (int i = 0; i < cur.length; i++) {
                   if (cur[i].home == HOME.Fraeport) {
                       return (cur[i].hl == HL.medal);
                   } 
               }
               return false
             },
             // nat is drinking whiskey
             { Entry[] cur -> 
               for (int i = 0; i < cur.length; i++) {
                   if (cur[i].person == PERSON.nat) {
                       return (cur[i].drink == DRINK.whiskey);
                   } 
               }
               return false
             },
             // person from Baelton drinking absinth
             { Entry[] cur -> 
               for (int i = 0; i < cur.length; i++) {
                   if (cur[i].home == HOME.Baelton) {
                       return (cur[i].drink == DRINK.absinth);
                   }
               }                       
               return false;
             },
             // Fitch is from Karnaka
             { Entry[] cur -> 
               for (int i = 0; i < cur.length; i++) {
                   if (cur[i].person == PERSON.fitch) {
                       return (cur[i].home == HOME.Karnaka);
                   } 
               }
               return false
             },
             //************************************************
             // assuming the ring - Dab - beer aren't in a row
             // ring next to Dab
             //{ Entry[] cur -> 
             //  for (int i = 1; i < cur.length - 1; i++) {
             //      if (cur[i].hl == HL.ring) {
             //          return (cur[i - 1].home == HOME.Dab || cur[i + 1].home == HOME.Dab);
             //      } else if (cur[i].home == HOME.Dab) {
             //          return (cur[i - 1].hl == HL.ring || cur[i + 1].hl == HL.ring);
             //      }
             //  }
             //  return false
             //},
             //// Dab next to beer
             //{ Entry[] cur -> 
             //  for (int i = 1; i < cur.length - 1; i++) {
             //      if (cur[i].home == HOME.Dab) {
             //          return (cur[i - 1].drink == DRINK.beer || cur[i + 1].drink == DRINK.beer);
             //      } else if (cur[i].drink == DRINK.beer) {
             //          return (cur[i - 1].home == HOME.Dab || cur[i + 1].home == HOME.Dab);
             //      }
             //  }
             //  return false
             //},
             //************************************************
             // assuming the ring - Dab - beer ARE in a row
             { Entry[] cur -> 
               for (int i = 1; i < cur.length - 1; i++) {
                   if (cur[i].home == HOME.Dab) {
                       return (cur[i - 1].drink == DRINK.beer && cur[i + 1].hl == HL.ring) ||
                       (cur[i - 1].hl == HL.ring && cur[i + 1].drink == DRINK.beer);
                   } 
               }
               return false
             },
           ]
    ;

def conteeblue = { Entry[] cur ->
                   ret = cur.findAll( {e -> e.person == PERSON.contee })
                   .findAll( {e -> e.color == COLOR.blue })
                   if (ret.size > 1)
                       println " PROBLEM: count of contee blues = " + ret
                           return ret.size == 1
                           };
def redleftofwhite = { Entry[] cur ->
                       for (int i = 0; i < 4; i++){
                           Entry e = cur[i];
                           if (e.color == COLOR.red)
                               return cur[i+1].color == COLOR.white
                       }
                       return false;
                       //boolean seenred = false;
                       //for (Entry e : cur) {
                       //    if (e.color == COLOR.white){
                       //        if (!seenred)
                       //            return false;
                       //        else
                       //            return true;
                       //    }
                       //    if (e.color == COLOR.red)
                       //        seenred = true
                       //}
};

def redwithwine = { Entry[] cur ->
                    for (Entry e : cur) {
                        if (e.color == COLOR.red) {
                            return (e.drink == DRINK.wine);
                        }
                    }
};

def dunwallgreen = { Entry[] cur ->
                    for (Entry e : cur) {
                        if (e.home == HOME.Dunwall) {
                            return (e.color == COLOR.green);
                        }
                    }
};



boolean apply(Closure rule, Integer[] person, Integer[] color, Integer[] drink = null, Integer[] home = null) {
    domain= new Entry[5];
    for (int pos = 0; pos < 5; pos ++) {
        Entry e = new Entry(POS.values()[pos],
                            PERSON.values()[person[pos]],
                            COLOR.values()[color[pos]],
                            drink == null ? null : DRINK.values()[drink[pos]],
                            home == null ? null : HOME.values()[home[pos]])
        domain[pos]= e;
    }

    return rule.call(domain)
}

for (Integer[] person : permus) {
    if (person[0] != PERSON.marcolla.ordinal())
        continue;
    for (Integer[] color : permus) {
        if (color[1] != COLOR.purple.ordinal())
            continue;
        if (!apply(conteeblue, person, color))
            continue;
        if (!apply(redleftofwhite, person, color))
            continue;
        for (Integer[] drink : permus) {
            if (drink[2] != DRINK.rum.ordinal())
                continue;
            if (!apply(redwithwine, person, color, drink))
                continue;
            for (Integer[] home : permus) {
                if (!apply(dunwallgreen, person, color, drink, home))
                    continue;
                for (Integer[] hl : permus) {
                    domain= new Entry[5];
                    for (int pos = 0; pos < 5; pos ++) {
                        Entry e = new Entry(POS.values()[pos],
                                            PERSON.values()[person[pos]],
                                            COLOR.values()[color[pos]],
                                            DRINK.values()[drink[pos]],
                                            HOME.values()[home[pos]],
                                            HL.values()[hl[pos]]
                                            );                                            
                        domain[pos]= e;
                    }
                    boolean truthyness = true;
                    for (int c = 0; c < rules.size && truthyness; c++) {
                        truthyness = rules[c].call(domain);
                    }
                    if (truthyness)
                        printEntries(domain)
                }
            }
        }
    }
}


public void permutations(List<Integer> permutes, Set<Integer> items, Stack<Integer> permutation, int size) {

    /* permutation stack has become equal to size that we require */
    if(permutation.size() == size) {
        /* print the permutation */
        permutes.add(permutation.toArray(new Integer[0]));
    }

    /* items available for permutation */
    Integer[] availableItems = items.toArray(new Integer[0]);
    for(Integer i : availableItems) {
        /* add current item */
        permutation.push(i);

        /* remove item from available item set */
        items.remove(i);

        /* pass it on for next permutation */
        permutations(permutes, items, permutation, size);

        /* pop and put the removed item back */
        items.add(permutation.pop());
    }
}
