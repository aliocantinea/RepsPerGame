package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility

sealed class OrderType {
    /*
At first I was going to have it so that the color of the exercise was the order type
this is because I wanted the ability to group body parts by color, such as bicep curls and
triceps both bring red, but I decided to copy Philipp Lackner's Ascending/Descending and add
a 4th color field to exercise order instead. This will allow for the user to group by color
for lets say upper body and use push-ups or rows as the exercise
*/
    object Ascending: OrderType()
    object Descending: OrderType()
}

// Source https://youtu.be/8YPXv7xKh2w?t=2102