;;special indentation for clojure
(eval-after-load 'clojure-mode
  '(define-clojure-indent
     (describe 'defun)
     (it 'defun)
     (should 'defun)
     (defprotocol 'defun)
     (defrecord 'defun)))
