/*
 * Encog(tm) Core v3.2 - Java Version
 * http://www.heatonresearch.com/encog/
 * http://code.google.com/p/encog-java/
 
 * Copyright 2008-2012 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package org.encog.app.generate.program;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.encog.ml.MLMethod;

public class EncogProgramNode extends EncogTreeNode {	
	private final List<EncogProgramArg> args = new ArrayList<EncogProgramArg>();	
	private final NodeType type;
	private final String name;
	
	public EncogProgramNode(EncogProgram theProgram, EncogTreeNode theParent, NodeType theNodeType, String theName) {
		super( theProgram, theParent);
		this.type = theNodeType;
		this.name = theName;
	}

	public List<EncogProgramArg> getArgs() {
		return args;
	}
	
	public void addArg(int argValue) {
		EncogProgramArg arg = new EncogProgramArg(argValue);
		this.args.add(arg);
	}
		
	public NodeType getType() {
		return type;
	}
	
	

	public String getName() {
		return name;
	}

	public void addArg(double argValue) {
		EncogProgramArg arg = new EncogProgramArg(argValue);
		this.args.add(arg);
	}
	
	public void addArg(String argValue) {
		EncogProgramArg arg = new EncogProgramArg(argValue);
		this.args.add(arg);
	}
	
	public void addArg(Object argValue) {
		EncogProgramArg arg = new EncogProgramArg(argValue);
		this.args.add(arg);
	}
	
	public EncogProgramNode createFunction(String theName) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.StaticFunction, theName);
		this.getChildren().add(node);
		return node;
	}

	public EncogProgramNode createMainFunction() {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.MainFunction, null);
		this.getChildren().add(node);
		return node;
	}

	public void defineConst(EncogArgType type, String name, String value) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.Const, name);
		node.addArg(value);
		node.addArg(type.toString());
		this.getChildren().add(node);		
	}

	public EncogProgramNode createFunctionCall(EncogProgramNode funct, String returnType, String returnVariable) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.FunctionCall, funct.getName());
		node.addArg(returnType);
		node.addArg(returnVariable);
		this.getChildren().add(node);
		return node;
		
	}

	public EncogProgramNode createNetworkFunction(String name, File method) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.CreateNetwork, name);
		node.addArg(method);
		this.getChildren().add(node);
		return node;
	}

	public EncogProgramNode createArray(String name, double[] a) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.InitArray, name);
		node.addArg(a);
		this.getChildren().add(node);
		return node;
	}

	public EncogProgramNode embedTraining(File data) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.EmbedTraining, "");
		node.addArg(data);
		this.getChildren().add(node);
		return node;		
	}

	public EncogProgramNode generateLoadTraining(File data) {
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.LoadTraining, "");
		node.addArg(data);
		this.getChildren().add(node);
		return node;
	}

	public EncogProgramNode createFunctionCall(String name, String returnType,
			String returnVariable) {
		
		EncogProgramNode node = new EncogProgramNode(getProgram(), this,
				NodeType.FunctionCall, name);
		node.addArg(returnType);
		node.addArg(returnVariable);
		this.getChildren().add(node);
		return node;

		
	}	
}
