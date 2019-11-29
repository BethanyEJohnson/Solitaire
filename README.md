# solitare
OOD Final Project


Changes Uploaded on 11/13:

Deleted all classes not used to make program run, add what you want back in.
It runs and sets the board up correctly.

Now we need to add it so when a card is clicked it can be moved, afterwards we would add the logic for card movement.



Changes Uploaded on 11/16:

Cards with faces can now be clicked and dragged, the deck can now be clicked and it will flip over the top card

Changes Uploaded on 11/25:

Card now will snap back to original position, logic needs to be added to look for the case that a card can stay at a point it is dragged to

Changes Uploaded on 11/28:

DeckOfCards (Singelton), you can click on space deck is initially in to flip deck back over when you cycle through it, collision is now being detected for cards, single cards can now be moved around between the stacks.

Task List:

- Fix Deck cycle, when deck is flipped back over it starts from bottom card
- Add logic to make previous card flip over when the card on the bottom of stack is moved
- Add logic to make cards fix in place after being moved
- Add logic to be able to move multiple cards (when they are stacked on top of each other)
- Add logic to stack cards in cache
- Implement design patterns
