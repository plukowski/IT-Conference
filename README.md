

# IT-Conference
Zadanie rekrutacyjne Sii  
Autor: Przemysław Łukowski  
  
  **URUCHAMIANIE PROJEKTU:**  
  -
Projekt wymaga narzędzia Maven, Spring Boot 2.7.0 i JDK obsługującego Java 11  
Uruchamiamy terminal w katalogu głównym projektu i wpisujemy polecenie 

> ./mvnw spring-boot:run

Alternatywnie można pobrać najnowszy release i wykorzystać uruchamialny plik .jar  
  
  
   **PRZYKŁADOWE ZAPYTANIA:**
   -
**1. Wyświetlenie planu konferencji:**  
typ: GET  
url: "/lecture/all"  
  
**2. Wyświetlenie rezerwacji użytkownika:**  
typ: GET  
url: "/user?login=Foo"  
  
*Parametr "login" określa login użytkownika, którego rezerwacje zostaną zwrócone przez serwer*
    
**3. Składanie rezerwacji:**  
typ: POST  
url: "/reservation?subjectId=1&period=1"  
ciało:  

    {
        "login": "Foo",
        "email": "Foo@bar.pl"
    }
    
  *Parametr "subjectId" określa, której ścieżki tematycznej dotyczy rezerwacja użytkownika, a "period" specyfikuje jednostkę czasową, podczas której dana prelekcja się odbywa.*  
  *W ciele należy przesłać obiekt z loginem i adresem e-mail użytkownika*  
    
**4. Odwołanie rezerwacji:**  
typ: DELETE  
url: "/reservation?subjectId=1&period=1"  
ciało:  

    {
        "login": "Foo",
        "email": "Foo@bar.pl"
    }
  
*Parametry i ciało wyznaczane są w ten sam sposób, jak przy składaniu rezerwacji*
  
**5. Zmiana adresu e-mail:**  
typ: PATCH  
url: "/user"  
ciało:  
  
    {
        "login": "Foo",
        "email": "Foo@bar.pl"
    }
  
  *W ciele należy przesłać obiekt z loginem i nowym adresem e-mail użytkownika*    
  
**6. Wyświetlenie listy użytkowników:**  
typ: GET  
url: "/user/all"  
  
**7. Wygenerowanie zestawienia wykładów według zainteresowania:**  
typ: GET  
url: "/lecture/stats?type=0"  
  

**8. Wygenerowanie zestawienia ścieżek tematycznych według zainteresowania**  
typ: GET  
url: "/lecture/stats?type=1"  


  
