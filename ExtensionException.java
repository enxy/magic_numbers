class ExtensionException extends Exception {
    // Parameterless Constructor
      public ExtensionException() {}

      // Constructor that accepts a message
      public ExtensionException(String message)
      {
         super(message);
      }
}