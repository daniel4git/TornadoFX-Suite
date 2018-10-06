package com.github.hd.tornadofxsuite.view

import com.github.hd.tornadofxsuite.app.Styles
import com.github.hd.tornadofxsuite.app.Styles.Companion.top
import com.github.hd.tornadofxsuite.app.Styles.Companion.translucent
import com.github.hd.tornadofxsuite.controller.FXTestBuilders
import com.github.hd.tornadofxsuite.model.TornadoFXInputsScope
import tornadofx.*

class Dialog : Fragment() {

    private val view: MainView by inject()
    private val testBuilder: FXTestBuilders by inject()

    override val scope = super.scope as TornadoFXInputsScope

    override val root = vbox {
        prefWidth = 600.0
        prefHeight = 400.0

        label("It looks like you have some controls in your project!  Shall I create some tests for them?") {
            prefWidth = 600.0
        }.addClass(top)

        stackpane {
            vboxConstraints {
                marginTopBottom(40.0)
                marginLeftRight(40.0)
            }

            listview<String> {
                scope.collection.forEach { view ->
                    items.add(view.key)
                    view.value.forEach { input ->
                        items.add("\t" + input)
                    }
                }
            }
        }

        button("yeaaa") {
            action {
                testBuilder.generateTests(scope.collection)
                view.overlay.removeClass(translucent)
                close()
            }
            vboxConstraints {
                marginLeft = 280.0
                marginBottom = 20.0
            }
        }

    }.addClass(Styles.main)

}