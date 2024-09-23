Link do repo  ` MartaM-a/git_training_Mmorints (github.com)`  
Link do pull request https://github.com/Kowal-D/git_training/pull/4

- Ściągnęłam  lokalnie zmiany z mojego repo /git_training_Mmorints za pomocą otwarcia nowego projektu  przy użyciu systemu kontroli wersji (project-version control) w Inteli J IDE
## Zadanie 1 - praca na repozytorium  
 Zmerdżowałam zmiany i rozwiązałam konflikt w InteliJ IDE
```PS C:\Users\mmorints\Projects\gitTrainings> git status                       On branch mainYour branch is ahead of 'origin/main' by 3 commits.  (use "git push" to publish your local commits)
nothing to commit, working tree cleanPS C:\Users\mmorints\Projects\gitTrainings> git logcommit ec6ae3326b1084dab8170ea6cf8cec6b697939cd (HEAD -> main)Merge: 397795e 2244ff6Author: MartaM-a <martamorincewa@gmail.com>Date:   Sun Sep 22 15:32:14 2024 +0200
    resolved conflict
commit 397795e3b7e790152182663e8d57bc45e987c3cd (origin/AdamNowak, AdamNowak)PS C:\Users\mmorints\Projects\gitTrainings>
```

## Tworzenie pliku .gitignor  
Do ` .gitignore` należy dodać dwa rodzaje plików
```
.idea -pliki konfiguracyjne IDE  
.iml pliki konfiguracji projektów
 ```

Dodane  rodzaje plików  w .gitignore nie zniknęły  na zdalnym repozytorium (Origin) Żeby te zmiany zostały wprowadzone należy użyć poleceń z

``` 
git rm -r --cached .idea
git rm -r – cached *.iml
a potem dokonać Push na Origin

 ```
## Branch Test  

 Dodanie zmian w branchy test  ` add developer` i branchy main `add version`.

Pierwszy problem pojawi się już przy próbie przełączenia branczy z main na test, ponieważ zmiany nie zostały dodane `git add`, i nie zostały zakomitiwane `git commit`. 

Możemy dodać zmiany (git add) lub pozostawić je w stash (git stash) z późniejszą opcją commitu.  

Po dokonaniu commita na branchy main, pojawią się nieścisłości pomiędzy main i test spowodowane dodanymi zmianami na obydwu branchach w jednym `pliku pom.xml`Na branchy test nie są widoczne zmiany dokonane na branchy main i odwrotnie.
Ten problem można rozwiązać na dwa sposoby :


1. Za pomocą `merge` - będąc na branży test domerdzować zmiany wprowadzone na main `git merge main`  Lub za pomocą `git rebase main` (będąc na branczy test) pozwala to przenieść wszystkie zmiany z main na branche test

```
PS C:\Users\mmorints\Projects\gitTrainings> git rebase main
Successfully rebased and updated refs/heads/test.
PS C:\Users\mmorints\Projects\gitTrainings> git log --oneline
d407dea (HEAD -> test) added developer
5e18333 (main) added version
f26bb60 (origin/trainigMartaMorintseva, trainigMartaMorintseva) deleted system files from origin
399b8ce added .gitignore
ec6ae33 resolved conflict
397795e (origin/AdamNowak, AdamNowak) adding user 4
2244ff6 (origin/JanKowalski, JanKowalski) adding user 3
345e314 (origin/main, origin/HEAD) preparing repository
PS C:\Users\mmorints\Projects\gitTrainings> git log 
commit d407deaa438997bd13467bd04399b9a466897445 (HEAD -> test)
Author: MartaM-a <martamorincewa@gmail.com>
Date:   Sun Sep 22 18:04:11 2024 +0200

    added developer

commit 5e18333087536067c035f572573a2fd91494b880 (main)
Author: MartaM-a <martamorincewa@gmail.com>
PS C:\Users\mmorints\Projects\gitTrainings> git status
On branch test
nothing to commit, working tree clean
PS C:\Users\mmorints\Projects\gitTrainings>


```


## Różnica między git checkout HEAD~3 i git reset --hard HEAD~3
`git checkout HEAD~3` przesuwa HEAD o trzy commity do tyłu bez usuwania poprzednich commitów w ten sposób możemy pracować na 4 commicie i mamy możliwość utworzenia z tego miejsca dodatkowych branch, lub wrocić do ostatniego commita. Nie zmienia historii i nie usuwa zmian.
`git reset` --hard HEAD~3 usuwa 3 poprzednie commity. Main i Head są w jednym w tym samym miejscu. Wszystkie zmiany w staging area i w working directory są wykasowane.

## Różnica pomiędzy git revert i git reset
`Git revert` usuwa zmiany we wskazanym commicie poprzez stworzenie nowego commitu. Co nie narusza historii commitów. Wykorzystywany jest kiedy zmiany zostały już wypuszowane  

`Git  reset`    
 są trzy opcje reset :  
 ```     
mixed  
soft               
 hard     
```

`git reset --mixed` jest wykonywany kiedy nie wskażemy konkretnie który z resetów chcemy wykonać . Polega on na tym,  że wszystkie zresetowane zmiany powracają do working directory. Commit jest kasowany.
`git reset --soft` – wszystkie zresetowane zmiany są zwracane do stage. Commit jest kasowany.
`git reset --hard` -  wszystkie zmiany są na trwale usuwane. Commit jest kasowany.

`git revert`
```
PS C:\Users\mmorints\Projects\gitTrainings> git revert 9af6659
Auto-merging pom.xml
CONFLICT (content): Merge conflict in pom.xml
error: could not revert 9af6659... added version
hint: After resolving the conflicts, mark them with
hint: "git add/rm <pathspec>", then run
hint: "git revert --continue".
hint: You can instead skip this commit with "git revert --skip".
hint: To abort and get back to the state before "git revert",
hint: run "git revert --abort".
hint: Disable this message with "git config advice.mergeConflict false"
PS C:\Users\mmorints\Projects\gitTrainings> git add .
PS C:\Users\mmorints\Projects\gitTrainings> git revert --continue
hint: Waiting for your editor to close the file... unix2dos: converting file C:/Users/mmorints/Projects/gitTrainings/.git/COMMIT_EDITMSG to DOS format...
dos2unix: converting file C:/Users/mmorints/Projects/gitTrainings/.git/COMMIT_EDITMSG to Unix format...
[test 03c8d3f] Revert "added version"
 1 file changed, 4 deletions(-)
PS C:\Users\mmorints\Projects\gitTrainings> git log
commit 03c8d3ff773b793dabc4fa1bbd4f6141335ff342 (HEAD -> test)
Author: MartaM-a <martamorincewa@gmail.com>
Date:   Sun Sep 22 21:17:24 2024 +0200

    Revert "added version"

    This reverts commit 9af66595192367afb284de132399dbde881163fc.

    deleted Kasia Kovalska

commit 867f268eb2f3be57009ecb08cf894182f57dfbf5
Author: MartaM-a <martamorincewa@gmail.com>



```



 `git reset`


```
commit 867f268eb2f3be57009ecb08cf894182f57dfbf5
Author: MartaM-a <martamorincewa@gmail.com>
PS C:\Users\mmorints\Projects\gitTrainings> git reset 9af6659
Unstaged changes after reset:
M       pom.xml
PS C:\Users\mmorints\Projects\gitTrainings> git status
On branch test
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   pom.xml

no changes added to commit (use "git add" and/or "git commit -a")
PS C:\Users\mmorints\Projects\gitTrainings> git add .
PS C:\Users\mmorints\Projects\gitTrainings> git commit -m "added reset ola nowak"
[test becad27] added reset ola nowak
 1 file changed, 4 deletions(-)
PS C:\Users\mmorints\Projects\gitTrainings>


```

### Git clean -n 


 Polecenie `Git clean -n`  pokazuje  wszystkie nie trackowane przez git pliki z danego projektu.  Polecenie `Git clean` usuwa wszystkie nie trackowane przez git pliki z danego projektu 

  
## Git remote prune -n origin
`Git remote prune -n origin`  pokazuje wszystkie gałęzie zdalne które nie mają połączenia z lokalnym repozytorium`Git remote prune origin` usuwa lokalne odniesienia do nieistniejących branchy na origin
## Usuwanie nieużywanych branchy

Nieużywanego brancha lokalnie można usunąć za pomocą polecenia `git branch -d nazwa_brancza` lub `git branch -D nazwa_brancza` - kiedy mamy nieścisłości związane z usunięciem(nie wykonane merdże) i git blokuje zwykłe usunięcie.  Nieużywanego brancha na origin można usunąć za pomocą polecenia `git push origin –delete nazwa_gałęzi` lub na github za pomocą delete.
## –no-commit 
 `–no-commit ` to polecenie powoduje, że przy wykonaniu np merge, możemy edytować commit. Dzięki temu można sprawdzić i dostosować wynik np. mergu przed jego zatwierdzeniem. 
Możemy stosować z różnymi poleceniami dodatkowymi gdzie występuje scalanie np: ```merge, revert, cherry pick, rebase.```  







