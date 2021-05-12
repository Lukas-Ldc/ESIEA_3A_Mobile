# ESIEA_3A_Mobile &nbsp; <img src="/app/src/main/res/drawable/bitcoin.png?raw=true" alt="Logo Bitcoin" width="30"> 

Petit projet de programmation mobile à l'ESIEA pour le semestre deux de la première année d'ingénieur.

Cette application mobile est développée en Kotlin avec Android Studio. Elle permet d'afficher les 30 cryptomonnaies avec la plus grosse capitalisation. Elle utilise pour cela l'API de <a href="https://www.coinlore.com/cryptocurrency-data-api" target="_blank">CoinLore</a> avec Retrofit. Le classement est affiché dans un RecyclerView et on a la possibilité de cliquer sur un élément. Un affichage différent nous montre alors des détails sur la cryptomonnaie et son logo récupéré avec Glide sur <a href="https://cryptoicons.org/" target="_blank">CryptoIcons</a>. Les informations (hors logo) sont stockées dans le SharedPreferences pour pouvoir les consulter même sans connexion internet.

Page d'accueil avec la liste des 30 cryptomonnaies :

<img src="/img.readme/main.png?raw=true" alt="Capture d'écran de l'application" width="250">

Page de détail d'une cryptomonnaie :

<img src="/img.readme/detail.png?raw=true" alt="Capture d'écran de l'application" width="250">
