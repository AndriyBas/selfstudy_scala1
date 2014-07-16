import natnums.{Succ, Zero}

val zero = Zero
val one = zero.successor

val two = new Succ(one)

val three = one + two

val seven = zero + Zero + one + zero + one + three + two + two + zero - one


