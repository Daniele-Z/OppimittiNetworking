<?php
  // Connessione al database MySQL
  $conn = mysqli_connect('localhost', 'root', '', 'sqd');
  if (!$conn) {
    die('Connessione al database fallita: ' . mysqli_connect_error());
  }

  // Ottieni l'ID del dipendente dalla query string
  $id = $_GET['id'];

  // Esegui la query per eliminare il dipendente dal database
  $query = "DELETE FROM dipendenti WHERE id = $id";
  $result = mysqli_query($conn, $query);
  header("Refresh:0; url=http://localhost/Dipendenti/dipendenti.php");
  if ($result) {
    echo "Dipendente eliminato con successo!";
    
    
  } else {
    echo "Errore durante l'eliminazione del dipendente.";
  }
  
  
  // Chiudi la connessione al database
  mysqli_close($conn);
?>







