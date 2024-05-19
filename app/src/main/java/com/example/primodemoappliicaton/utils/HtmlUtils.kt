package com.example.primodemoappliicaton.utils

fun addImageStyleToHtml(html: String): String {
    return """
        <html>
        <head>
            <style>
                img { 
                    max-width: 100%; 
                    height: auto; 
                }
            </style>
        </head>
        <body>
            $html
        </body>
        </html>
    """.trimIndent()
}