<?php
  // Connessione al database MySQL
  $conn = mysqli_connect('localhost', 'root', '', 'sqd');
  if (!$conn) {
    die('Connessione al database fallita: ' . mysqli_connect_error());
  }

  // Ottieni i valori inviati tramite il formulario di modifica
  $id = $_POST['id'];
  $nome = $_POST['Nome'];
  $cognome = $_POST['Cognome'];
  $sesso = $_POST['Sesso'];
  $nascita = $_POST['Nascita'];
  $stipendio = $_POST['Stipendio'];
  $impiego = $_POST['Impiego'];

  // Esegui la query per aggiornare i dati del dipendente nel database
  $query = "UPDATE dipendenti SET Nome = '$nome', Cognome = '$cognome', Sesso = '$sesso', Nascita = '$nascita', Stipendio = '$stipendio', Impiego = '$impiego' WHERE id = $id";
  $result = mysqli_query($conn, $query);

  if ($result) {
    echo "Modifica salvata con successo!";
    header("Refresh:0; url=http://localhost/Dipendenti/dipendenti.php");
  } else {
    echo "Errore durante il salvataggio della modifica.";
  }

  // Chiudi la connessione al database
  mysqli_close($conn);
?>