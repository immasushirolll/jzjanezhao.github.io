default: movieTheaterDB_actor.o movieTheaterDB_movie.o
    gcc -o movieTheaterDB movieTheaterDB.c $^

movieTheaterDB_actor.o: movieTheaterDB_actor.c movieTheaterDB_actor.h
    gcc -c $<

movieTheaterDB_movie.o: movieTheaterDB_movie.c movieTheaterDB_movie.h
    gcc -c $<

clean:
    rm movieTheaterDB*.o
    [ -f "./movieTheaterDB" ] && rm movieTheaterDB
 
