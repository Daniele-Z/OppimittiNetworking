<?php
  // Connessione al database MySQL
  $conn = mysqli_connect('localhost', 'root', '', 'sqd');
  if (!$conn) {
    die('Connessione al database fallita: ' . mysqli_connect_error());
  }

  // Ottieni i valori inviati tramite il form di ricerca
  $nome = $_POST['Nome'];
  $cognome = $_POST['Cognome'];
  $impiego = $_POST['Impiego'];

  // Esegui la query di ricerca dei dipendenti
  $query = "SELECT * FROM dipendenti WHERE Nome = '$nome' AND Cognome = '$cognome'";
  if (!empty($impiego)) {
    $query .= " AND Impiego = '$impiego'";
  }
  $result = mysqli_query($conn, $query);

  // Stampa i risultati della ricerca
  echo "<h2>Risultati della Ricerca</h2>";
  echo "<table>";
  echo "<tr>";
  echo "<th>Nome</th>";
  echo "<th>Cognome</th>";
  echo "<th>Sesso</th>";
  echo "<th>Nascita</th>";
  echo "<th>Stipendio</th>";
  echo "<th>Impiego</th>";
  echo "</tr>";
  while ($row = mysqli_fetch_assoc($result)) {
    echo "<tr>";
    echo "<td>" . $row['Nome'] . "</td>";
    echo "<td>" . $row['Cognome'] . "</td>";
    echo "<td>" . $row['Sesso'] . "</td>";
    echo "<td>" . $row['Nascita'] . "</td>";
    echo "<td>" . $row['Stipendio'] . "</td>";
    echo "<td>" . $row['Impiego'] . "</td>";
    echo "</tr>";
  }
  echo "</table>";

  // Chiudi la connessione al database
  mysqli_close($conn);
?>